/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.Tipo;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Aluno
 */
@Entity
@Table(name = "ferramenta", catalog = "lojaagricolaifpa", schema = "")
@NamedQueries({
    @NamedQuery(name = "Ferramenta.findAll", query = "SELECT f FROM Ferramenta f")
    , @NamedQuery(name = "Ferramenta.findById", query = "SELECT f FROM Ferramenta f WHERE f.id = :id")
    , @NamedQuery(name = "Ferramenta.findByNome", query = "SELECT f FROM Ferramenta f WHERE f.nome = :nome")
    , @NamedQuery(name = "Ferramenta.findByCusto", query = "SELECT f FROM Ferramenta f WHERE f.custo = :custo")
    , @NamedQuery(name = "Ferramenta.findByQuantidade", query = "SELECT f FROM Ferramenta f WHERE f.quantidade = :quantidade")
    , @NamedQuery(name = "Ferramenta.findByDepreciacao", query = "SELECT f FROM Ferramenta f WHERE f.depreciacao = :depreciacao")
    , @NamedQuery(name = "Ferramenta.findByTipoId", query = "SELECT f FROM Ferramenta f WHERE f.tipoId = :tipoId")})
public class Ferramenta implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "custo")
    private float custo;
    @Basic(optional = false)
    @Column(name = "quantidade")
    private int quantidade;
    @Basic(optional = false)
    @Column(name = "depreciacao")
    private float depreciacao;
    @Basic(optional = false)
    @Column(name = "tipo_id")
    private int tipoId;
    @JoinColumn(name = "tipo_id", referencedColumnName = "id", updatable = false, insertable = false)
    @ManyToOne
    private Tipo tipoId2;

    public Ferramenta() {
    }

    public Ferramenta(Integer id) {
        this.id = id;
    }

    public Ferramenta(Integer id, String nome, float custo, int quantidade, float depreciacao, int tipoId) {
        this.id = id;
        this.nome = nome;
        this.custo = custo;
        this.quantidade = quantidade;
        this.depreciacao = depreciacao;
        this.tipoId = tipoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        String oldNome = this.nome;
        this.nome = nome;
        changeSupport.firePropertyChange("nome", oldNome, nome);
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        float oldCusto = this.custo;
        this.custo = custo;
        changeSupport.firePropertyChange("custo", oldCusto, custo);
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        int oldQuantidade = this.quantidade;
        this.quantidade = quantidade;
        changeSupport.firePropertyChange("quantidade", oldQuantidade, quantidade);
    }

    public float getDepreciacao() {
        return depreciacao;
    }

    public void setDepreciacao(float depreciacao) {
        float oldDepreciacao = this.depreciacao;
        this.depreciacao = depreciacao;
        changeSupport.firePropertyChange("depreciacao", oldDepreciacao, depreciacao);
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        int oldTipoId = this.tipoId;
        this.tipoId = tipoId;
        changeSupport.firePropertyChange("tipoId", oldTipoId, tipoId);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ferramenta)) {
            return false;
        }
        Ferramenta other = (Ferramenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "loja_agricula.Ferramenta[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public Tipo getTipoId2() {
        return tipoId2;
    }

    public void setTipoId2(Tipo tipoId2) {
        Tipo oldTipoId2 = this.tipoId2;
        this.tipoId2 = tipoId2;
        changeSupport.firePropertyChange("tipoId2", oldTipoId2, tipoId2);
    }
    
}
