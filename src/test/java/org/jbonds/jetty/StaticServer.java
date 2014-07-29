package org.jbonds.jetty;

import com.google.common.io.CharStreams;
import lombok.Value;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Value
public class StaticServer {

    public static void startServer(final String request, final String response) throws Exception {
        Thread serverThread = new Thread() {

            public void run() {
                final Server server = new Server(8080);
                server.setStopAtShutdown(true);
                ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
                context.setContextPath("/");
                server.setHandler(context);
                context.addServlet(new ServletHolder(new StaticResponseServlet(request, response)),"/*");
                try {
                    server.start();
                    server.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
        serverThread.start();
    }

    private static class StaticResponseServlet extends HttpServlet
    {
        private final String requestString;
        private final String responseString;

        public StaticResponseServlet(String request, String response)
        {
            this.requestString = request;
            this.responseString = response;
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print(responseString);
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            if (!requestString.equals(CharStreams.toString(request.getReader()))) {
                throw new RuntimeException("Unexpected request");
            }

            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print(responseString);
        }
    }

}
