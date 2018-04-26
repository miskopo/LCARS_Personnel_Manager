package cz.muni.fi.pv168.web;

import cz.muni.fi.pv168.common.ServiceFailureException;
import cz.muni.fi.pv168.common.ShipType;
import cz.muni.fi.pv168.model.Ship;
import cz.muni.fi.pv168.model.ShipManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Katarina Bulkova
 */
@WebServlet(ShipServlet.URL_MAPPING + "/*")
public class ShipServlet extends HttpServlet {

    private static final String LIST_JSP = "/list_ships.jsp";
    public static final String URL_MAPPING = "/ship";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showShipList(request, response);
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
                String designation = request.getParameter("designation");
                String type = request.getParameter("type");
                String warpCapabilitiesString = request.getParameter("warpCapabilities");
                //form data validity check
                if (name == null || name.length() == 0 || designation == null || designation.length() ==0 ||
                        type == null || warpCapabilitiesString == null) {
                    request.setAttribute("error", "All fields are compulsory!");
                    showShipList(request, response);
                    return;
                }
                //form data processing - storing to database
                try {
                    // TODO: ID chould not be compulsory
                    Ship ship = new Ship(1L, name, designation, ShipType.valueOf(type), Double.valueOf(warpCapabilitiesString));
                    getShipManager().createShip(ship);
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
                    getShipManager().deleteShipByID(id);
                    response.sendRedirect(request.getContextPath()+URL_MAPPING);
                    return;
                } catch (ServiceFailureException e) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }
            case "/update":
//                Long id = Long.valueOf(request.getParameter("id"));
//                Ship shipBackup = getShipManager().getShip(id);
//                return;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown action " + action);
        }
    }

    /**
     * Gets BookManager from ServletContext
     *
     * @return BookManager instance
     */
    private ShipManager getShipManager() {
            return (ShipManager) getServletContext().getAttribute("shipManager");
    }

    /**
     * Stores the list of books to request attribute "books" and forwards to the JSP to display it.
     */
    private void showShipList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("ship", getShipManager().findAllShips());
            request.getRequestDispatcher(LIST_JSP).forward(request, response);
        } catch (ServiceFailureException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
