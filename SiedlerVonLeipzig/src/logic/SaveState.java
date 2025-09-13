package logic;

import java.io.Serializable;
import java.util.ArrayList;

import buildings.Buildings;
import settlers.ASettlers;
import items.Items;

public class SaveState implements Serializable {
    private static final long serialVersionUID = 1L;
    public final ArrayList<Buildings> buildings;
    public final ArrayList<ASettlers> settlers;
    public final ArrayList<Items> items;
    
    public SaveState(ArrayList<Buildings> b, ArrayList<ASettlers> s, ArrayList<Items> i) {
        this.buildings = b; this.settlers = s; this.items = i;
    }
    
    
}
