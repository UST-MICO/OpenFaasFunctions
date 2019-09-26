# AddElements Function

This function can be used to add certain elements to a message

#### Note
To maintain the CloudEvent format, the operation is only applied on the child elements of the `data` element

#### Example
```bash
>>> curl http://127.0.0.1:8080/function/flatten -d '{"data" : {"key" : "value"}}'

{"data":{"added_val_1":23,"added_val_2":"foo","key":"value"}}
```

