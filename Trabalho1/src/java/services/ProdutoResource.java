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
 * @author Drico
 */
@Path("produto")
public class ProdutoResource {

    @Context
    private UriInfo context;

    public ProdutoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        Gson gson = new Gson();
        return gson.toJson(DAOProduto.getAll());
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    @POST
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public boolean inserirProduto(String content) {
        Gson gson = new Gson();
        Produto ca = gson.fromJson(content, Produto.class);
        JsonObject convertedObject = gson.fromJson(content, JsonObject.class);
        String idCategoria = convertedObject.get("categoria_id").getAsString();
        Long IdCategoria = Long.parseLong(idCategoria);
        System.out.println("long id");
        Categoria cat = DAOCategoria.getOne(IdCategoria);
        System.out.println("pegou a categoria");
        ca.setCategoria(cat);
        System.out.println("setou a categoria");
        return DAOProduto.persist(ca);
    }

    @Path("{produtoId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getProduto(@PathParam("produtoId") String id) {
        Gson gson = new Gson();
        Produto ct = new Produto();
        ct = DAOProduto.getOne(Long.parseLong(id));
        return gson.toJson(ct);
    }

    @Path("excluir/{produtoId}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean excluir(@PathParam("produtoId") String id) {
        return DAOProduto.excluir(Long.parseLong(id));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean alterarProduto(String content) {
        Gson gson = new Gson();
        Produto p = (Produto) gson.fromJson(content, Produto.class);
        JsonObject convertedObject = gson.fromJson(content, JsonObject.class);
        String idCategoria = convertedObject.get("categoria_id").getAsString();
        Long IdCategoria = Long.parseLong(idCategoria);
        System.out.println("long id");
        Categoria cat = DAOCategoria.getOne(IdCategoria);
        System.out.println("pegou a categoria");
        p.setCategoria(cat);
        System.out.println("setou a categoria");
        return DAOProduto.editar(p);
    }

}
