package io.grpc.examples.helloworld;

import com.futurewei.alcor.schema.GoalStateProvisionerGrpc;
import com.futurewei.alcor.schema.Goalstate;
import com.futurewei.alcor.schema.Goalstateprovisioner;
import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends GoalStateProvisionerGrpc.GoalStateProvisionerImplBase {
  @Override
  public void pushNetworkResourceStates(
      Goalstate.GoalState request,
      StreamObserver<Goalstateprovisioner.GoalStateOperationReply> responseObserver) {
    final Goalstateprovisioner.GoalStateOperationReply build =
        Goalstateprovisioner.GoalStateOperationReply.newBuilder().build();
    responseObserver.onNext(build);
    responseObserver.onCompleted();
  }
}
