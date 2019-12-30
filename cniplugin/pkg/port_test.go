package pkg

import (
	"testing"
)

func TestParseGetPortResp(t *testing.T) {
	subnetID := "a87e0f87-a2d9-44ef-9194-9a62f178594e"

	tcs := []struct{
		desc string
		responseBody string
		expectedIP, expectedMAC, expectedStatus string
		expectingError bool
	} {
		{
			desc: "single record",
			responseBody: `
{
  "portId":"1111111",
  "status":"UP",
  "macAddress":"02:42:32:43:60:bf",
  "fixedIps":[
    {"subnetId":"----", "ipAddress":"123.45.67.8"}
  ]
}
`,			expectedIP: "123.45.67.8",
			expectedMAC: "02:42:32:43:60:bf",
			expectedStatus: "UP",
		},
		{
			desc: "multi records",
			responseBody: `
{
  "portId":"1111111",
  "status":"WIP",
  "macAddress":"00:11:22:33:44:55",
  "fixedIps":[
    {"subnetId":"11111111-1111-1111-1111-111111111111", "ipAddress":"1.1.1.1"},
    {"subnetId":"a87e0f87-a2d9-44ef-9194-9a62f178594e", "ipAddress":"2.2.2.2"}
  ]
}
`,			expectedIP: "2.2.2.2",
			expectedMAC: "00:11:22:33:44:55",
			expectedStatus: "WIP",
		},
		{
			desc: "no ip record",
			responseBody: `
{
  "portId":"1111111",
  "status":"UP",
  "macAddress":"02:42:32:43:60:bf",
  "fixedIps":[]
}
`,			expectingError: true,
		},
	}

	for _, tc := range tcs {
		info, err := parseGetPortResp(subnetID, []byte(tc.responseBody))

		if !tc.expectingError {
			if err != nil {
				t.Errorf("unexpected error: %v", err)
			}

			if err == nil {
				if tc.expectedIP != info.IP {
					t.Errorf("%q: expected %v, got %v", tc.desc, tc.expectedIP, info.IP)
				}
				if tc.expectedMAC != info.MAC {
					t.Errorf("%q: expected %q, got %q", tc.desc, tc.expectedMAC, info.MAC)
				}
				if tc.expectedStatus != string(info.Status) {
					t.Errorf("%q: expected %q, got %q", tc.desc, tc.expectedStatus, info.Status)
				}
			}
		}

		if tc.expectingError {
			if err == nil {
				t.Errorf("%q: expected error; got nil", tc.desc)
			} else {
				t.Logf("%q: expected error; got %v", tc.desc, err)
			}
		}
	}
}

func TestGenCreatePortBody(t *testing.T) {
	projectID := "3dda2801-d675-4688-a63f-dcda8d327f50"
	subnetID := "a87e0f87-a2d9-44ef-9194-9a62f178594e"
	portID := "12345678-abcd-efgh-ijkl-ABCDEFGHIJKL"
	targetHost := "myhost"
	targetNS := "/run/netns/myns"
	targetNIC := "eth0"
	cniSandbox := "cafe123456"

	expectedBody := `
{
  "port": {
    "project_id": "3dda2801-d675-4688-a63f-dcda8d327f50",
    "id": "12345678-abcd-efgh-ijkl-ABCDEFGHIJKL",
    "name": "k8s_12345678-abcd-efgh-ijkl-ABCDEFGHIJKL",
    "admin_state_up": true,
    "description": "cni cafe123456, ns:/run/netns/myns, host:myhost",
    "network_id": "a87e0f87-a2d9-44ef-9194-9a62f178594e",
    "veth_name": "eth0",
    "veth_namespace": "/run/netns/myns",
    "dns_domain": "my-domain.org.",
    "dns_name": "myport",
    "port_security_enabled": false,
    "binding:host_id": "myhost"
  }
}
`
	body, err := genCreatePortBody(projectID, subnetID, portID, targetHost, targetNIC, targetNS, cniSandbox)
	if err != nil {
		t.Errorf("unexpected error: %v", err)
	}

	if body != expectedBody {
		t.Errorf("\nexpected %q, \n     got %q\n", expectedBody, body)
	}
}