package controllers;

import com.google.inject.Inject;
import models.Product;
import play.data.*;
import play.data.validation.ValidationError;
import play.libs.Json;
import play.mvc.*;

import java.util.*;

public class ProductController extends Controller {
    @Inject
    private FormFactory formFactory;

    private DynamicForm dynamicForm;

    public Result index() {
        List<Product> products = Product.all();
        if (products.isEmpty()) {
            return Results.notFound("商品は見つかりません");
        } else {
            return Results.ok(Json.toJson(products));
        }
    }

    public Result create() {
        Form<Product> form = formFactory.form(Product.class).bindFromRequest();
        if (form.hasErrors()) {
            List<String> validationError = new ArrayList<>();
            for (ValidationError e : form.allErrors()) {
                validationError.add(e.message());
            }
            return Results.badRequest(Json.toJson(validationError));
        }
        Product in = form.get();
        Product.create(in);
        return Results.ok(Json.toJson(in));
    }

    public Result update(Long id) {
        Product p = Product.findById(id);
        Form<Product> form = formFactory.form(Product.class).bindFromRequest();
        if (p != null) {
            if (form.hasErrors()) {
                List<String> validationError = new ArrayList<>();
                for (ValidationError e : form.allErrors()) {
                    validationError.add(e.message());
                }
                return Results.badRequest(Json.toJson(validationError));
            }
            p.updateWith(form.get().title, form.get().text, form.get().price, form.get().image);
            return Results.ok(Json.toJson(p));
        } else {
            return Results.notFound("選択した商品は見つかりません");
        }
    }

    public Result destroy(Long id) {
        Product p = Product.findById(id);
        if (p != null) {
            p.deleteProduct();
            return Results.ok(Json.toJson(p));
        } else {
            return Results.notFound("選択した商品は見つかりません");
        }
    }

    public Result search() {
        String text = dynamicForm.bindFromRequest().get("text");
        List<Product> products = Product.findBytextLikeIncomplete(text);
        if (products.isEmpty()) {
            return Results.notFound("選択した商品は見つかりません");
        }
        return Results.ok(Json.toJson(products));
    }
}
