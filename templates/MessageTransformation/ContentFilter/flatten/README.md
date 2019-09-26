# Flatten Function

This function can be used to flatten a message, whose elements are deeply nested in groups.

#### Note
To maintain the CloudEvent format, the operation is only applied on the child elements of the `data` element

#### Example
```bash
>>> curl http://127.0.0.1:8080/function/flatten -d '{"data": {"a": 1, "c": {"a": 2, "b": {"x": 5, "y" : 10}}, "d": [1, 2, 3]}}'

{"data": {"a": 1, "c_a": 2, "c_b_x": 5, "c_b_y": 10, "d": [1, 2, 3]}}
```

