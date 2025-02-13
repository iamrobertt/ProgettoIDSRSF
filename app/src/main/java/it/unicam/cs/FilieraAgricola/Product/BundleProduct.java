package it.unicam.cs.FilieraAgricola.Product;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("BUNDLE")
@Data
public class BundleProduct extends Product{


    @OneToMany(mappedBy = "parentBundle", cascade = CascadeType.ALL)
    private List<BundleItem> bundleItems;

    public BundleProduct(long bundleID,
                         String bundleName,
                         String bundleDescription,
                         double bundlePrice,
                         int bundleQuantity,
                         ProductState bundleState,
                         ProductType bundleType,
                         List<BundleItem> bundleItems
    ) {
        super(bundleID, bundleName, bundleDescription, bundlePrice, bundleQuantity, bundleState, ProductType.BUNDLE);
        this.bundleItems = bundleItems;
    }


    public BundleProduct() {}


    @Override
    public void setProductPrice(double productPrice) {
        double price = 0.0;

        for(BundleItem item : this.bundleItems)
            price += item.getProduct().getProductPrice();

        this.productPrice = price;
    }


    public void add(BundleItem bundleItem) {
        if(bundleItem == null)
            throw new NullPointerException("Product is null");
        this.bundleItems.add(bundleItem);
    }


}
