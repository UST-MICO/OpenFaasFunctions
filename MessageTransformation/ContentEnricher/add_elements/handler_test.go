package function

import (
	"encoding/json"
	"testing"
)

// This test checks if the user defined data `addedData` in handler.go is added in the response`
func TestHandle(t *testing.T) {
	req := []byte(`{
		"specversion" : "0.2",
		"type" : "io.github.ust.mico.result",
		"source" : "/router",
		"id" : "A234-1234-1234",
		"time" : "2019-05-08T17:31:00Z",
		"contenttype" : "application/json",
		"data" : {
			"key" : "value"
		}
	}`)
	keys := []string{"key", "val_1", "val_2"}
	var responseData map[string]interface{}
	response := Handle(req)
	// fmt.Println(string(response))
	json.Unmarshal(response, &responseData)
	data := responseData["data"].(map[string]interface{})
	for dkey, _ := range data {
		found := 0
		for _, tkey := range keys {
			if dkey == tkey {
				found = 1
			}
		}
		if found == 0 {
			t.Errorf("The key '%s' was not found", dkey)
		}
	}
}
