package br.edu.utfpr.cp.lesson07;

public class Main {}

interface Document {
    String getValueAsString();
}

class CPF implements Document {

    @Override
    public String getValueAsString() {
        return "CPF";
    }
}


class RG implements Document {
    @Override
    public String getValueAsString() {
        return "RG";
    }
}

class BirthCertificate implements Document {
    @Override
    public String getValueAsString() {
        return "Birth Certificate";
    }
}

interface Person {
    String getTypeAsString();
    Document getDocument();

    default String getDocumentValue() {
        return this.getDocument().getValueAsString();
    }
}

class Child implements Person {
    @Override
    public String getTypeAsString() {
        return "Child";
    }

    @Override
    public Document getDocument() {
        return new BirthCertificate();
    }
}

class Teenager implements Person {
    @Override
    public String getTypeAsString() {
        return "Teenager";
    }

    @Override
    public Document getDocument() {
        return new RG();
    }
}

class Adult implements Person {
    @Override
    public String getTypeAsString() {
        return "Adult";
    }

    @Override
    public Document getDocument() {
        return new CPF();
    }
}