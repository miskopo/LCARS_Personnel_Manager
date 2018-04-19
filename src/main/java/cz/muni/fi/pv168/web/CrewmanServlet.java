package cz.muni.fi.pv168.web;

import cz.muni.fi.pv168.common.Rank;
import cz.muni.fi.pv168.common.ServiceFailureException;
import cz.muni.fi.pv168.model.Crewman;
import cz.muni.fi.pv168.model.CrewmanManager;
import cz.muni.fi.pv168.model.CrewmanManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Michal Polovka
 */
@WebServlet(CrewmanServlet.URL_MAPPING + "/*")
public class CrewmanServlet extends HttpServlet {

    private static final String LIST_JSP = "/list.jsp";
    public static final String URL_MAPPING = "/crewman";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showCrewmanList(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //support non-ASCII characters in form
        request.setCharacterEncoding("utf-8");
        //action specified by pathInfo
        String action = request.getPathInfo();
        switch (action) {
            case "/add":
                //getting POST parameters from form
                String name = request.getParameter("name");
                String rank = request.getParameter("rank");
                //form data validity check
                if (name == null || name.length() == 0 || rank == null || rank.length() == 0) {
                    request.setAttribute("error", "All fields are compulsory!");
                    showCrewmanList(request, response);
                    return;
                }
                //form data processing - storing to database
                try {
                    // TODO: ID chould not be compulsory
                    Crewman crewman = new Crewman(1L, name, Rank.valueOf(rank));
                    getCrewmanManager().createCrewman(crewman);
                    //redirect-after-POST protects from multiple submission
                    response.sendRedirect(request.getContextPath()+URL_MAPPING);
                    return;
                } catch (ServiceFailureException e) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }
            case "/delete":
                try {
                    Long id = Long.valueOf(request.getParameter("id"));
                    getCrewmanManager().deleteCrewman(id);
                    response.sendRedirect(request.getContextPath()+URL_MAPPING);
                    return;
                } catch (ServiceFailureException e) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }
            case "/update":
                Long id = Long.valueOf(request.getParameter("id"));
                Crewman crewmanBackup = getCrewmanManager().getCrewman(id);
                return;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown action " + action);
        }
    }

    /**
     * Gets BookManager from ServletContext
     *
     * @return BookManager instance
     */
    private CrewmanManager getCrewmanManager() {
            return (CrewmanManager) getServletContext().getAttribute("crewmanManager");
    }

    /**
     * Stores the list of books to request attribute "books" and forwards to the JSP to display it.
     */
    private void showCrewmanList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("crewman", getCrewmanManager().findAllCrewmen());
            request.getRequestDispatcher(LIST_JSP).forward(request, response);
        } catch (ServiceFailureException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
