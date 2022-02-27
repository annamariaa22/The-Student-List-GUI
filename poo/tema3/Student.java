public class Student implements Comparable<Student> {
    String nume;
    String prenume;
    int prezenta;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getPrezente() {
        return prezenta;
    }

    public void setPrezente(int prezenta) {
        this.prezenta = prezenta;
    }

    @Override
    public String toString() {
        return nume + " " + prenume + " " + prezenta;
    }

    @Override
    public int compareTo(Student elementDeComparat) {
        Student studentDeComparat = elementDeComparat;
        if (prezenta == studentDeComparat.prezenta) {
            return 0;
        } else if (prezenta > studentDeComparat.prezenta) {
            return 1;
        } else {
            return -1;
        }
    }

}