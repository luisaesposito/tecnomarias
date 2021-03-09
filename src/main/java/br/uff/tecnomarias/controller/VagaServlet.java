package br.uff.tecnomarias.controller;

import br.uff.tecnomarias.domain.entity.Vaga;
import br.uff.tecnomarias.service.VagaService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VagaServlet extends HttpServlet {

    @Inject
    VagaService vagaService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Vaga vaga = new Vaga();
        vaga.setAreaAtuacao(request.getParameter("areaAtuacao"));
        vaga.setCargo(request.getParameter("cargo"));
        vaga.setDescricao(request.getParameter("descricao"));
        Long id = vagaService.salvar(vaga).getId();

        ServletContext servcontext = request.getServletContext();
        if (id != null) {
            RequestDispatcher dispatcher = servcontext.getRequestDispatcher("/VagaCadastrada.jsp");
            dispatcher.include(request, response);
        } else {
            RequestDispatcher dispatcher = servcontext.getRequestDispatcher("/ErroCadastroVaga.jsp");
            dispatcher.include(request, response);
        }
    }
}
