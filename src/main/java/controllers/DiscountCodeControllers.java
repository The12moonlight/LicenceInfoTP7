/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelPackage.DAOExtends;
import modelPackage.DiscountCodeEntity;
import simplejdbc.DAOException;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author pierr
 */
@WebServlet(name = "DiscountCodeControllers", urlPatterns = {"/DiscountCodeControllers"})
public class DiscountCodeControllers extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try { 
            // Créér le ExtendedDAO avec sa source de données 
            DAOExtends dao = new DAOExtends(DataSourceFactory.getDataSource());
            
            // ON récuperer les parametre
            String action = request.getParameter("action") ;
            String code = request.getParameter("code");
            String taux = request.getParameter("taux");
            List<DiscountCodeEntity> discountEntity = dao.listDiscountCode();
            request.setAttribute("listDiscountCode", discountEntity);
            
            if(action != null) {
                if (action.equals("ADD")) {
                    request.setAttribute("code", code);
                    request.setAttribute("taux",taux);
                    // On verifie que le code saisie n'existe pas deja dans la table sinon erreur
                    // TODO FINIR <= affichage d'une page blanche si deux fois le meme code
//                    for (int i = 0; i < discountEntity.size(); i++){
//                        String codeIpos = discountEntity.get(0).toString();
//                        if (code.equals(codeIpos)){
//                            request.setAttribute("code", code);
//                            request.setAttribute("errorMessage","Erreur code deja dans la base de donnée");
//                            request.getRequestDispatcher("views/Error.jsp").forward(request, response);
//                        }
//                    }
                    dao.addDiscountCode(code, Float.parseFloat(taux));
                    discountEntity = dao.listDiscountCode();
                    request.setAttribute("listDiscountCode", discountEntity);
                } else if (action.equals("DELETE")) {
                    request.setAttribute("code",code);
                    dao.deleteDiscountCode(code);
                    discountEntity = dao.listDiscountCode();
                    request.setAttribute("listDiscountCode", discountEntity);
                }
            }
            
            // On continue vers la page JSP sélectionnée
            request.getRequestDispatcher("views/DiscountCodeViews.jsp").forward(request, response);
        } catch (DAOException ex) {
            Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DiscountCodeControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DiscountCodeControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
