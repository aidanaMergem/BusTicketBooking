package com.busticketbooking.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bus_station")
public class BusStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "busStations", fetch = FetchType.LAZY)
    private Set<City> cities;

    @OneToMany(mappedBy = "busStation", cascade = CascadeType.ALL)
    private Set<Route> routes;
}
