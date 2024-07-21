    package com.eventosfull.fullapi.model;

    import com.fasterxml.jackson.annotation.JsonFormat;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import java.util.Date;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Eventos {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String titulo;
        private String organizadora;
        
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        private Date data;
        private String descricao;
        
    }
