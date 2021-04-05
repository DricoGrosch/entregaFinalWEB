/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javax.ws.rs.DELETE;
import models.Categoria;
import models.Produto;

/**
 * REST Web Service
 *
 * @author Drico
 */
@Path("categoria")
public class CategoriaResource {

    @Context
    private UriInfo context;

    public CategoriaResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        Gson gson = new Gson();
        return gson.toJson(DAOCategoria.getAll());
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    @POST
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    
    public boolean inserirProduto(String content) {
        Gson gson = new Gson();
        Categoria ca = (Categoria) gson.fromJson(content, Categoria.class);
        return DAOCategoria.persist(ca);

    }

    @Path("{categoriaId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCategoria(@PathParam("categoriaId") String id) {
        Gson gson = new Gson();
        Categoria ct = new Categoria();
        ct = DAOCategoria.getOne(Long.parseLong(id));
        return gson.toJson(ct);
    }

    @Path("excluir/{categoriaId}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean excluir(@PathParam("categoriaId") String id) {
        return DAOCategoria.excluir(Long.parseLong(id));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean alterarCategoria(String content) {
        Gson gson = new Gson();
        Categoria ct = (Categoria) gson.fromJson(content, Categoria.class);
        return DAOCategoria.editar(ct);
    }

}
