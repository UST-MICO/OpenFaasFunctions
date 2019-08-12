package function

import (
	"encoding/json"
)

// Modify this map to for adding your own values
var addedData = map[string]interface{}{
	"val_1": 23,
	"val_2": "foo",
}

// This function adds the values in `addedData` to any incoming message.`
func Handle(msg []byte) []byte {
	var msgData map[string]interface{}
	json.Unmarshal(msg, &msgData)
	data := msgData["data"].(map[string]interface{})
	for k, v := range addedData {
		data[k] = v
	}
	response, _ := json.Marshal(msgData)
	return response
}
