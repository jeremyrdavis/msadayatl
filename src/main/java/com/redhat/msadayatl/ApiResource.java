package com.redhat.msadayatl;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApiResource {

    @GET
    public List<Todo> allTodos() {

        return Todo.listAll();
    }

    @POST
    @Transactional
    public Response addTodo(Todo todo) {

        todo.persist();
        return Response.created(URI.create("/" + todo.id)).entity(todo).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public Response updateTodo(@PathParam("id") Long id, Todo updatedTodo) {

        Todo todo = Todo.findById(id);
        todo.completed = updatedTodo.completed;
        todo.order = updatedTodo.order;
        todo.title = updatedTodo.title;
        todo.persist();

        return Response.ok().entity(todo).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteTodo(@PathParam("id") Long id) {

        Todo todo = Todo.findById(id);
        todo.delete();

        return Response.ok().build();
    }

}