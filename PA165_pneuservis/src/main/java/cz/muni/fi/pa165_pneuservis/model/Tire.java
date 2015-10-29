/*
 * Team project for course PA165 - Enterprise Applications in Java
 * For more informations see file README.md
 */
package cz.muni.fi.pa165_pneuservis.model;

import javax.persistence.Entity;

/**
 *
 * @author Jozef.Sumaj
 */
@Entity
public class Tire extends Item {
    private String brand;
    private Integer width;
    private Integer ratio;
    private Integer rim;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }

    public Integer getRim() {
        return rim;
    }

    public void setRim(Integer rim) {
        this.rim = rim;
    }
}
