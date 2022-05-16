package com.example.projetsem2qrcode.data;

public class DataTestImpl implements DataTest{
    @Override
    public String idBase() {
        return "1";
    }

    @Override
    public String nomEtudiantBase() {
        return "Guyot";
    }

    @Override
    public String prenomEtudiant() {
        return "Clement";
    }

    @Override
    public String numEtudiant() {
        return "2163453";
    }

    @Override
    public String groupTp() {
        return "Tp1";
    }

    @Override
    public boolean emargementEtudiantTrue() {
        return true;
    }

    @Override
    public boolean emargementEtudiantFalse() {
        return false;
    }

    @Override
    public String numEtudiantNull() {
        return null;
    }

    @Override
    public String numEtudiantBlanck() {
        return "     ";
    }

    @Override
    public String nomEtudiantBlanck() {
        return "    ";
    }

    @Override
    public String prenomEtudiantBlanck() {
        return "      ";
    }

    @Override
    public String nomEtudiantNull() {
        return null;
    }

    @Override
    public String prenomEtudiantNull() {
        return null;
    }
}
