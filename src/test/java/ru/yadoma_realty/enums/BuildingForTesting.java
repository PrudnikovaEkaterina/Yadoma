package ru.yadoma_realty.enums;

public enum BuildingForTesting {
    ZHK_WOODS(17440),
    ZHK_LUCHI(9232),
    ZHK_AVIATIKA(14962);

    public final int id;
    BuildingForTesting(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
