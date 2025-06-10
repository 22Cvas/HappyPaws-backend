package org.ncapas.happypawsbackend.Domain.Enums;

public enum ApplicationState {
    PENDIENTE("Pendiente"),
    ACEPTADA("Aceptada"),
    RECHAZADA("Rechazada");

    private final String label;

    ApplicationState(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
