# OpenFaas Functions

This repository contains some prepared OpenFaas functions that can be used for transforming or routing of messages (CloudEvents).

## Helpful commands

Get logs of function:
```bash
kubectl -n $NAMESPACE logs -f $(kubectl get pods -n $NAMESPACE --selector=faas_function=$FUNCTION_NAME --output=jsonpath={.items..metadata.name})
```
