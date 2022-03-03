package com.example.demo.dto.ResponseTotalVentaPorFechas;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@NoArgsConstructor
public class ResponseTotalVentaPorDiaDTO {
//    private Date date;
    private Long income;

    public ResponseTotalVentaPorDiaDTO(Long income) {
//        this.date = date;
        this.income = income;
    }
}
