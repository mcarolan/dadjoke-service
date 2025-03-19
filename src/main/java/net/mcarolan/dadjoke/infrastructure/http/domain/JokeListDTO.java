package net.mcarolan.dadjoke.infrastructure.http.domain;

import java.util.List;

public record JokeListDTO(List<JokeDTO> jokes) { }
