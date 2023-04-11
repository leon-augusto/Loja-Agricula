/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.Unidade;
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
@Table(name = "insumo", catalog = "lojaagricolaifpa", schema = "")
@NamedQueries({
    @NamedQuery(name = "Insumo.findAll", query = "SELECT i FROM Insumo i")
    , @NamedQuery(name = "Insumo.findById", query = "SELECT i FROM Insumo i WHERE i.id = :id")
    , @NamedQuery(name = "Insumo.findByNome", query = "SELECT i FROM Insumo i WHERE i.nome = :nome")
    , @NamedQuery(name = "Insumo.findByQuantidade", query = "SELECT i FROM Insumo i WHERE i.quantidade = :quantidade")
    , @NamedQuery(name = "Insumo.findByCusto", query = "SELECT i FROM Insumo i WHERE i.custo = :custo")
    , @NamedQuery(name = "Insumo.findByValidade", query = "SELECT i FROM Insumo i WHERE i.validade = :validade")
    , @NamedQuery(name = "Insumo.findByTipoId", query = "SELECT i FROM Insumo i WHERE i.tipoId = :tipoId")
    , @NamedQuery(name = "Insumo.findByUnidadeId", query = "SELECT i FROM Insumo i WHERE i.unidadeId = :unidadeId")})
public class Insumo implements Serializable {

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
    @Column(name = "quantidade")
    private int quantidade;
    @Basic(optional = false)
    @Column(name = "custo")
    private float custo;
    @Column(name = "validade")
    private Integer validade;
    @Basic(optional = false)
    @Column(name = "tipo_id")
    private int tipoId;
    @Basic(optional = false)
    @Column(name = "unidade_id")
    private int unidadeId;
    @JoinColumn(name = "unidade_id", referencedColumnName = "id", updatable = false, insertable = false)
    @ManyToOne
    private Unidade unidadeId2;

    public Insumo() {
    }

    public Insumo(Integer id) {
        this.id = id;
    }

    public Insumo(Integer id, String nome, int quantidade, float custo, int tipoId, int unidadeId) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.custo = custo;
        this.tipoId = tipoId;
        this.unidadeId = unidadeId;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        int oldQuantidade = this.quantidade;
        this.quantidade = quantidade;
        changeSupport.firePropertyChange("quantidade", oldQuantidade, quantidade);
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        float oldCusto = this.custo;
        this.custo = custo;
        changeSupport.firePropertyChange("custo", oldCusto, custo);
    }

    public Integer getValidade() {
        return validade;
    }

    public void setValidade(Integer validade) {
        Integer oldValidade = this.validade;
        this.validade = validade;
        changeSupport.firePropertyChange("validade", oldValidade, validade);
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        int oldTipoId = this.tipoId;
        this.tipoId = tipoId;
        changeSupport.firePropertyChange("tipoId", oldTipoId, tipoId);
    }

    public int getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(int unidadeId) {
        int oldUnidadeId = this.unidadeId;
        this.unidadeId = unidadeId;
        changeSupport.firePropertyChange("unidadeId", oldUnidadeId, unidadeId);
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
        if (!(object instanceof Insumo)) {
            return false;
        }
        Insumo other = (Insumo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "loja_agricula.Insumo[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public Unidade getUnidadeId2() {
        return unidadeId2;
    }

    public void setUnidadeId2(Unidade unidadeId2) {
        Unidade oldUnidadeId2 = this.unidadeId2;
        this.unidadeId2 = unidadeId2;
        changeSupport.firePropertyChange("unidadeId2", oldUnidadeId2, unidadeId2);
    }
    
}
