package bank.model.client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "tb_corporations")
public class Corporation extends Client {
    @Column(name = "cnpj")
    private String cnpj;

    public Corporation() {}

    public Corporation(String name, String cnpj) {
        super(name);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "Corporation{" + super.getDescriptionString() + ", cnpj='" + cnpj + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Corporation)) return false;
        if (!super.equals(o)) return false;
        Corporation that = (Corporation) o;
        return Objects.equals(cnpj, that.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cnpj);
    }
}
