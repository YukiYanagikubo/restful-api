package models;

import io.ebean.*;
import io.ebean.annotation.*;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "products")
public class Product extends Model {
    private static final Finder<Long, Product> find = new Finder<>(Product.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Constraints.Required(message = "必須項目です。")
    @Constraints.MaxLength(message = "100文字を越えています。", value = 100)
    public String title;

    @Constraints.Required(message = "必須項目です。")
    @Constraints.MaxLength(message = "500文字を越えています。", value = 500)
    public String text;

    @Constraints.Required(message = "必須項目です。")
    public Integer price;

    public String image;

    @CreatedTimestamp
    public Date createdAt;

    @UpdatedTimestamp
    public Date updatedAt;

    public Product() {
    }

    public static List<Product> all() {
        return Product.find.all();
    }

    public static Product find(Long id) {
        return Product.find.byId(id);
    }

    public static List<Product> findBytextLikeIncomplete(String text) {
        List<Product> datas = Product.find.query().where().ilike("title", "%" + text + "%").orderBy("createdAt").findList();
        return datas;
    }

    public static void create(Product in) {
        Product p = new Product();
        p.title = in.title;
        p.text = in.text;
        p.price = in.price;
        p.image = in.image;
        p.save();
    }

    public static Product findById(Long id) {
        return Product.find.byId(id);
    }

    public void updateWith(String title, String text, Integer price, String image) {
        this.title = title;
        this.text = text;
        this.price = price;
        this.image = image;
        update();
    }

    public void deleteProduct() {
        delete();
    }

}
