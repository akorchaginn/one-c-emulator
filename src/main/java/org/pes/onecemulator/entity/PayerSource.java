package org.pes.onecemulator.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payer_source")
public class PayerSource extends AbstractEntity {

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "payer_id")
    private Payer payer;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id")
    private Source source;

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
