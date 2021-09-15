package br.com.zup.model

import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class Compra(
    @field:NotBlank val produto: String,
    @field:NotNull @field:Min(value = 0L) val valorFrete: Double
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long? = null

}