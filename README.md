# OpenFaas Functions

This repository contains some prepared OpenFaas functions that can be used for transforming or routing of messages (CloudEvents).

## Deployment

Login to OpenFaaS Gateway:
```bash
echo -n $PASSWORD | faas-cli login --password-stdin
```

Deploy function:
```bash
faas deploy
```
Using up instand of deploy results in a build, push and deploy.

**Kubernetes:**

Port forwarding to OpenFaaS Gateway:
```bash
kubectl port-forward svc/gateway -n openfaas 8080
```

## Helpful commands

Get logs of function running in Kubernetes cluster:
```bash
kubectl -n $NAMESPACE logs -f $(kubectl get pods -n $NAMESPACE --selector=faas_function=$FUNCTION_NAME --output=jsonpath={.items..metadata.name})
```
