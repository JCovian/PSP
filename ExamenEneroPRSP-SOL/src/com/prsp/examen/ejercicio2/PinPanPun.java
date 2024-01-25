package com.prsp.examen.ejercicio2;

import java.util.concurrent.Semaphore;

public class PinPanPun {

    //Con Semáforos tenemos que pensar en hilos compartiendo recursos o turnos contables, limitados.
    //De inicio, sólo el semáforo pin permite a un hilo ejecutarse. Los otros dos comienzan sin turnos disponibles.
    private Semaphore semPin = new Semaphore(1), semPan = new Semaphore(0), semPun = new Semaphore(0);

    public void pin(){
        try {
            semPin.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.print("PIN ");
        semPan.release();
    }

    public void pan(){
        try {
            semPan.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.print("PAN ");
        semPun.release();
    }

    public void pun(){
        try {
            semPun.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.print("PUN ");
        semPin.release();
    }




}
