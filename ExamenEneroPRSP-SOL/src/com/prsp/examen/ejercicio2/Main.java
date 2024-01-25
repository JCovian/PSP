package com.prsp.examen.ejercicio2;

public class Main {
    public static final int REPETICIONES = 50;

    public static void main(String[] args) {
        PinPanPun controladorMensajes = new PinPanPun();

        Thread pin = new Thread(() -> {
            for (int i = 0; i < REPETICIONES; i++) {
                controladorMensajes.pin();
            }
        });

        Thread pan = new Thread(() -> {
            for (int i = 0; i < REPETICIONES; i++) {
                controladorMensajes.pan();
            }
        });

        Thread pun = new Thread(() -> {
            for (int i = 0; i < REPETICIONES; i++) {
                controladorMensajes.pun();
            }
        });

        pin.start();
        pan.start();
        pun.start();
    }
}

