package com.devcorp.psiconote;

import com.devcorp.psiconote.dtos.PsicologoToSaveDto;
import com.devcorp.psiconote.dtos.RolToSaveDto;
import com.devcorp.psiconote.dtos.UsuarioToSaveDto;
import com.devcorp.psiconote.dtos.mappers.PsicologoMapper;
import com.devcorp.psiconote.dtos.mappers.UsuarioMapper;
import com.devcorp.psiconote.entities.Psicologo;
import com.devcorp.psiconote.repository.PsicologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
public class StartupConfig {
    @Autowired
    private PsicologoRepository psicologoRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            // Crea y guarda el objeto
            List<RolToSaveDto> roles=List.of(new RolToSaveDto("ADMIN"));

            Psicologo psicologo= PsicologoMapper.instancia.
                    toSaveDtoToEntity(new PsicologoToSaveDto("Daniel",
                            "Pinilla",
                            "activo",
                            20,
                            "3242879891",
                            new UsuarioToSaveDto("pini@gmail.com",
                                            "pbarrera123",
                                            "202010",
                                            roles)));
            psicologoRepository.save(psicologo);
        };
    }
}
