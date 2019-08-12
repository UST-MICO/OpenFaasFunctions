# Element Remover

This function can be used to filter out certain elements from an incoming message.
The elements, that shall be removed are referenced manually by the user in the variable 'keys'.

#### Example
In `element_remover/handler.py` the variable `keys` holds the following values: 
```python
...
# The keys of the elements, that shall be removed
keys = ['testKey1', 'testKey2']
...
```
With this setting, the function provides the following result: 
```bash
>>> curl http://127.0.0.1:8080/function/element_remover -d '{"testKey1": 1, "testKey2": 2, "testKey3": 3}'

{"testKey3": 3}
```
