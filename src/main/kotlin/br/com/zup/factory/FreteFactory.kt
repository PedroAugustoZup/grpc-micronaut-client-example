package br.com.zup.factory

import br.com.zup.FreteServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import jakarta.inject.Singleton

@Factory
class FreteFactory {

    @Singleton
    fun fretesCliente(@GrpcChannel("fretes") chanel: ManagedChannel): FreteServiceGrpc.FreteServiceBlockingStub{
        return FreteServiceGrpc.newBlockingStub(chanel)
    }
}