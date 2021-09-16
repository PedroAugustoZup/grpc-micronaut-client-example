package br.com.zup.controller

import br.com.zup.ErroDetails
import br.com.zup.FreteRequest
import br.com.zup.FreteServiceGrpc
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.protobuf.StatusProto
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue


@Controller("/compra")
class CompraController(val grpcClient:FreteServiceGrpc.FreteServiceBlockingStub) {

    @Get
    fun salvarCompra(@QueryValue cep: String): HttpResponse<Any>{
        val request = FreteRequest.newBuilder()
            .setCep(cep)
            .build()
        try {
            val response = grpcClient.salvar(request)
            return HttpResponse.ok(response.valor)
        }catch (e: StatusRuntimeException){
            val status = e.status
            val statusCode = status.code
            val description = status.description

            if(statusCode == Status.Code.INVALID_ARGUMENT)
                return HttpResponse.badRequest(description)
            if(statusCode == Status.Code.PERMISSION_DENIED){
                val statusProto = StatusProto.fromThrowable(e)

                statusProto ?: return HttpResponse.status<Any>(HttpStatus.FORBIDDEN).body(description)

                val anyDetails = statusProto.detailsList.get(0)
                val details = anyDetails.unpack(ErroDetails::class.java)

                return HttpResponse.status<Any>(HttpStatus.FORBIDDEN)
                    .body("{${details.codigo}:${details.menssagem}")
            }

            return HttpResponse.badRequest()
        }
    }
}