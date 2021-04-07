package io.grpc.examples.helloworld;

import com.futurewei.alcor.schema.GoalStateProvisionerGrpc;
import com.futurewei.alcor.schema.Goalstate;
import com.futurewei.alcor.schema.Goalstateprovisioner;
import com.google.gson.Gson;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class GrpcClient2 {
  public static void main(String[] args) {

    ManagedChannel channel1 =
        ManagedChannelBuilder.forAddress("localhost", Integer.parseInt(args[2]))
            .usePlaintext()
            .build();
    Gson g = new Gson();
    final String s[] = new String[5];

    s[0] =
        "{\"bitField0_\":0,\"formatVersion_\":0,\"vpcStates_\":[],\"subnetStates_\":[{\"operationType_\":4,\"configuration_\":{\"formatVersion_\":1,\"revisionNumber_\":0,\"requestId_\":\"\",\"id_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88000\",\"networkType_\":0,\"projectId_\":\"3dda2801-d675-4688-a63f-dcda8d327f50\",\"vpcId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88039\",\"name_\":\"\",\"cidr_\":\"192.168.1.0/24\",\"tunnelId_\":88888,\"dhcpEnable_\":false,\"availabilityZone_\":\"\",\"primaryDns_\":\"\",\"secondaryDns_\":\"\",\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1635362226},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1317113639}],\"portStates_\":[{\"operationType_\":0,\"configuration_\":{\"bitField0_\":0,\"formatVersion_\":1,\"revisionNumber_\":1,\"requestId_\":\"\",\"id_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d00011\",\"messageType_\":1,\"networkType_\":0,\"projectId_\":\"3dda2801-d675-4688-a63f-dcda8d327f50\",\"vpcId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88039\",\"name_\":\"port1\",\"macAddress_\":\"AA-BB-CC-A1-50-C7\",\"adminStateUp_\":true,\"fixedIps_\":[{\"subnetId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88000\",\"ipAddress_\":\"10.0.0.22\",\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1563217895}],\"allowAddressPairs_\":[],\"securityGroupIds_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1974759623},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":922520989}],\"neighborStates_\":[],\"securityGroupStates_\":[{\"operationType_\":0,\"configuration_\":{\"bitField0_\":0,\"formatVersion_\":0,\"revisionNumber_\":0,\"requestId_\":\"\",\"id_\":\"\",\"projectId_\":\"\",\"vpcId_\":\"\",\"name_\":\"\",\"securityGroupRules_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0}],\"dhcpStates_\":[{\"operationType_\":0,\"configuration_\":{\"bitField0_\":0,\"formatVersion_\":1,\"revisionNumber_\":1,\"requestId_\":\"\",\"subnetId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88000\",\"macAddress_\":\"AA-BB-CC-A1-50-C7\",\"ipv4Address_\":\"10.0.0.22\",\"ipv6Address_\":\"\",\"portHostName_\":\"\",\"extraDhcpOptions_\":[],\"dnsEntryList_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0}],\"routerStates_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0}";
    s[1] =
        "{\"bitField0_\":0,\"formatVersion_\":0,\"vpcStates_\":[],\"subnetStates_\":[{\"operationType_\":3,\"configuration_\":{\"formatVersion_\":1,\"revisionNumber_\":0,\"requestId_\":\"\",\"id_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88000\",\"networkType_\":0,\"projectId_\":\"3dda2801-d675-4688-a63f-dcda8d327f50\",\"vpcId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88039\",\"name_\":\"\",\"cidr_\":\"192.168.1.0/24\",\"tunnelId_\":88888,\"dhcpEnable_\":false,\"availabilityZone_\":\"\",\"primaryDns_\":\"\",\"secondaryDns_\":\"\",\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1635362226},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1317113639}],\"portStates_\":[{\"operationType_\":0,\"configuration_\":{\"bitField0_\":0,\"formatVersion_\":1,\"revisionNumber_\":1,\"requestId_\":\"\",\"id_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d00012\",\"messageType_\":1,\"networkType_\":0,\"projectId_\":\"3dda2801-d675-4688-a63f-dcda8d327f51\",\"vpcId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88039\",\"name_\":\"port1\",\"macAddress_\":\"AA-BB-CC-A1-50-C7\",\"adminStateUp_\":true,\"fixedIps_\":[{\"subnetId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88000\",\"ipAddress_\":\"10.0.0.22\",\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1563217895}],\"allowAddressPairs_\":[],\"securityGroupIds_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1974759623},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":922520989}],\"neighborStates_\":[],\"securityGroupStates_\":[{\"operationType_\":0,\"configuration_\":{\"bitField0_\":0,\"formatVersion_\":0,\"revisionNumber_\":0,\"requestId_\":\"\",\"id_\":\"\",\"projectId_\":\"\",\"vpcId_\":\"\",\"name_\":\"\",\"securityGroupRules_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0}],\"dhcpStates_\":[{\"operationType_\":0,\"configuration_\":{\"bitField0_\":0,\"formatVersion_\":1,\"revisionNumber_\":1,\"requestId_\":\"\",\"subnetId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88000\",\"macAddress_\":\"AA-BB-CC-A1-50-C7\",\"ipv4Address_\":\"10.0.0.22\",\"ipv6Address_\":\"\",\"portHostName_\":\"\",\"extraDhcpOptions_\":[],\"dnsEntryList_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0}],\"routerStates_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0}";
    s[2] =
        "{\"bitField0_\":0,\"formatVersion_\":0,\"vpcStates_\":[],\"subnetStates_\":[{\"operationType_\":2,\"configuration_\":{\"formatVersion_\":1,\"revisionNumber_\":0,\"requestId_\":\"\",\"id_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88000\",\"networkType_\":0,\"projectId_\":\"3dda2801-d675-4688-a63f-dcda8d327f50\",\"vpcId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88039\",\"name_\":\"\",\"cidr_\":\"192.168.1.0/24\",\"tunnelId_\":88888,\"dhcpEnable_\":false,\"availabilityZone_\":\"\",\"primaryDns_\":\"\",\"secondaryDns_\":\"\",\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1635362226},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1317113639}],\"portStates_\":[{\"operationType_\":0,\"configuration_\":{\"bitField0_\":0,\"formatVersion_\":1,\"revisionNumber_\":1,\"requestId_\":\"\",\"id_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d00013\",\"messageType_\":1,\"networkType_\":0,\"projectId_\":\"3dda2801-d675-4688-a63f-dcda8d327f52\",\"vpcId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88039\",\"name_\":\"port1\",\"macAddress_\":\"AA-BB-CC-A1-50-C7\",\"adminStateUp_\":true,\"fixedIps_\":[{\"subnetId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88000\",\"ipAddress_\":\"10.0.0.22\",\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1563217895}],\"allowAddressPairs_\":[],\"securityGroupIds_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1974759623},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":922520989}],\"neighborStates_\":[],\"securityGroupStates_\":[{\"operationType_\":0,\"configuration_\":{\"bitField0_\":0,\"formatVersion_\":0,\"revisionNumber_\":0,\"requestId_\":\"\",\"id_\":\"\",\"projectId_\":\"\",\"vpcId_\":\"\",\"name_\":\"\",\"securityGroupRules_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0}],\"dhcpStates_\":[{\"operationType_\":0,\"configuration_\":{\"bitField0_\":0,\"formatVersion_\":1,\"revisionNumber_\":1,\"requestId_\":\"\",\"subnetId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88000\",\"macAddress_\":\"AA-BB-CC-A1-50-C7\",\"ipv4Address_\":\"10.0.0.22\",\"ipv6Address_\":\"\",\"portHostName_\":\"\",\"extraDhcpOptions_\":[],\"dnsEntryList_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0}],\"routerStates_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0}";
    s[3] =
        "{\"bitField0_\":0,\"formatVersion_\":0,\"vpcStates_\":[],\"subnetStates_\":[{\"operationType_\":1,\"configuration_\":{\"formatVersion_\":1,\"revisionNumber_\":0,\"requestId_\":\"\",\"id_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88000\",\"networkType_\":0,\"projectId_\":\"3dda2801-d675-4688-a63f-dcda8d327f50\",\"vpcId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88039\",\"name_\":\"\",\"cidr_\":\"192.168.1.0/24\",\"tunnelId_\":88888,\"dhcpEnable_\":false,\"availabilityZone_\":\"\",\"primaryDns_\":\"\",\"secondaryDns_\":\"\",\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1635362226},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1317113639}],\"portStates_\":[{\"operationType_\":0,\"configuration_\":{\"bitField0_\":0,\"formatVersion_\":1,\"revisionNumber_\":1,\"requestId_\":\"\",\"id_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d00014\",\"messageType_\":1,\"networkType_\":0,\"projectId_\":\"3dda2801-d675-4688-a63f-dcda8d327f53\",\"vpcId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88039\",\"name_\":\"port1\",\"macAddress_\":\"AA-BB-CC-A1-50-C7\",\"adminStateUp_\":true,\"fixedIps_\":[{\"subnetId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88000\",\"ipAddress_\":\"10.0.0.22\",\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1563217895}],\"allowAddressPairs_\":[],\"securityGroupIds_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1974759623},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":922520989}],\"neighborStates_\":[],\"securityGroupStates_\":[{\"operationType_\":0,\"configuration_\":{\"bitField0_\":0,\"formatVersion_\":0,\"revisionNumber_\":0,\"requestId_\":\"\",\"id_\":\"\",\"projectId_\":\"\",\"vpcId_\":\"\",\"name_\":\"\",\"securityGroupRules_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0}],\"dhcpStates_\":[{\"operationType_\":0,\"configuration_\":{\"bitField0_\":0,\"formatVersion_\":1,\"revisionNumber_\":1,\"requestId_\":\"\",\"subnetId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88000\",\"macAddress_\":\"AA-BB-CC-A1-50-C7\",\"ipv4Address_\":\"10.0.0.22\",\"ipv6Address_\":\"\",\"portHostName_\":\"\",\"extraDhcpOptions_\":[],\"dnsEntryList_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0}],\"routerStates_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0}";
    s[4] =
        "{\"bitField0_\":0,\"formatVersion_\":0,\"vpcStates_\":[],\"subnetStates_\":[{\"operationType_\":0,\"configuration_\":{\"formatVersion_\":1,\"revisionNumber_\":0,\"requestId_\":\"\",\"id_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88000\",\"networkType_\":0,\"projectId_\":\"3dda2801-d675-4688-a63f-dcda8d327f50\",\"vpcId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88039\",\"name_\":\"\",\"cidr_\":\"192.168.1.0/24\",\"tunnelId_\":88888,\"dhcpEnable_\":false,\"availabilityZone_\":\"\",\"primaryDns_\":\"\",\"secondaryDns_\":\"\",\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1635362226},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1317113639}],\"portStates_\":[{\"operationType_\":0,\"configuration_\":{\"bitField0_\":0,\"formatVersion_\":1,\"revisionNumber_\":1,\"requestId_\":\"\",\"id_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d00015\",\"messageType_\":1,\"networkType_\":0,\"projectId_\":\"3dda2801-d675-4688-a63f-dcda8d327f54\",\"vpcId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88039\",\"name_\":\"port1\",\"macAddress_\":\"AA-BB-CC-A1-50-C7\",\"adminStateUp_\":true,\"fixedIps_\":[{\"subnetId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88000\",\"ipAddress_\":\"10.0.0.22\",\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1563217895}],\"allowAddressPairs_\":[],\"securityGroupIds_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":1974759623},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":922520989}],\"neighborStates_\":[],\"securityGroupStates_\":[{\"operationType_\":0,\"configuration_\":{\"bitField0_\":0,\"formatVersion_\":0,\"revisionNumber_\":0,\"requestId_\":\"\",\"id_\":\"\",\"projectId_\":\"\",\"vpcId_\":\"\",\"name_\":\"\",\"securityGroupRules_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0}],\"dhcpStates_\":[{\"operationType_\":0,\"configuration_\":{\"bitField0_\":0,\"formatVersion_\":1,\"revisionNumber_\":1,\"requestId_\":\"\",\"subnetId_\":\"9192a4d4-ffff-4ece-b3f0-8d36e3d88000\",\"macAddress_\":\"AA-BB-CC-A1-50-C7\",\"ipv4Address_\":\"10.0.0.22\",\"ipv6Address_\":\"\",\"portHostName_\":\"\",\"extraDhcpOptions_\":[],\"dnsEntryList_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0}],\"routerStates_\":[],\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{},\"fieldsDescending\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0}";

    GoalStateProvisionerGrpc.GoalStateProvisionerBlockingStub stub1 =
        GoalStateProvisionerGrpc.newBlockingStub(channel1);
    final Goalstate.GoalState gs2 = g.fromJson(s[3], Goalstate.GoalState.class);

    stub1.pushNetworkResourceStates(gs2);
    for (int i = Integer.parseInt(args[4]); i < Integer.parseInt(args[5]); i++) {
      final ManagedChannel channel =
          ManagedChannelBuilder.forAddress(args[0], i).usePlaintext().build();
      final ManagedChannel channel2 =
          ManagedChannelBuilder.forAddress(args[1], i).usePlaintext().build();

      GoalStateProvisionerGrpc.GoalStateProvisionerBlockingStub stub =
          GoalStateProvisionerGrpc.newBlockingStub(channel);
      GoalStateProvisionerGrpc.GoalStateProvisionerBlockingStub stub2 =
          GoalStateProvisionerGrpc.newBlockingStub(channel2);
      final Goalstate.GoalState goalState = g.fromJson(s[i % 5], Goalstate.GoalState.class);
      new Thread(
              () -> {
                try {

                  final long start = System.currentTimeMillis();

                  final Goalstateprovisioner.GoalStateOperationReply goalStateOperationReply =
                      stub.pushNetworkResourceStates(goalState);
                  System.out.println(
                      Thread.currentThread() + " " + (System.currentTimeMillis() - start) + " ms");
                  System.out.println(
                      Thread.currentThread()
                          + ""
                          + goalStateOperationReply.getOperationStatusesList());
                } catch (Exception e) {
                  e.printStackTrace();
                } finally {
                  if (channel != null) {
                    try {
                      channel.awaitTermination(5, TimeUnit.SECONDS);

                    } catch (Exception e) {
                      e.printStackTrace();
                    }
                  }
                }
              })
          .start();

      new Thread(
              () -> {
                try {

                  final long start = System.currentTimeMillis();

                  final Goalstateprovisioner.GoalStateOperationReply goalStateOperationReply2 =
                      stub2.pushNetworkResourceStates(goalState);
                  System.out.println(
                      Thread.currentThread() + " " + (System.currentTimeMillis() - start) + " ms");
                  System.out.println(
                      Thread.currentThread()
                          + ""
                          + goalStateOperationReply2.getOperationStatusesList());
                } catch (Exception e) {
                  e.printStackTrace();
                } finally {
                  if (channel != null) {
                    try {
                      channel.awaitTermination(5, TimeUnit.SECONDS);
                    } catch (Exception e) {
                      e.printStackTrace();
                    }
                  }
                }
              })
          .start();
    }
  }
}
