package Authentication;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Authenticate", urlPatterns = {"/Authenticate"})
public class Authenticate extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        Local
//        String path = getServletConfig().getServletContext().getRealPath("WEB-INF");
//        Openshift
        String path = "";
        String filename = "";
        String resPath = "";
        URL url;
        if(!("OPENSHIFT_DATA_DIR".equals(null))){
            path = System.getenv("OPENSHIFT_DATA_DIR");
            filename = path + "/forumCredentials.txt";
        } else{
            ServletContext context = request.getSession().getServletContext();
            url = context.getResource("/WEB-INF/forumCredentials.txt");
            resPath = url.getPath();
        }
        File file = new File(resPath + "forumCredentials.txt");
        UserHandler userHandler = new UserHandler(file);
        Thread threadUserHandler = new Thread(userHandler);
        threadUserHandler.start();
        String page;
        String action = request.getParameter("action");
        switch (action) {
            case "signin":
                page = "SignIn.jsp";
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                boolean auth = userHandler.checkCredentials(username, password);
                if (auth) {
                    request.getSession().setAttribute("username", username);
                    page = "Welcome.jsp";
                } else {
                        request.setAttribute("error", "Login Failure: Unknown Username or Bad Password.");
                }

                request.getRequestDispatcher(page).forward(request, response);
                break;
            case "signout":
                request.getSession().invalidate();
                request.setAttribute("msg", "Successful Logout.");
                page = "SignIn.jsp";
                request.getRequestDispatcher(page).forward(request, response);
                break;
            case "signup":
                page = "SignUp.jsp";
                username = request.getParameter("username");
                password = request.getParameter("password");
                String verify = request.getParameter("verify");
                if (password.equals(verify)) {
                    boolean saved = userHandler.saveUser(username, password);
                    if (saved) {
                        request.setAttribute("msg", "You successfully signed up, you can now sign in.");
                        page = "SignIn.jsp";
                    } else {
                        request.setAttribute("error", "This username is already taken.");
                    }
                }
                else {
                    request.setAttribute("error", "Your password doesn't match.");
                }

                request.getRequestDispatcher(page).forward(request, response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}