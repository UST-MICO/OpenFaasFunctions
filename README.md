# OpenFaas Functions

This repository contains some prepared OpenFaas functions that can be used for transforming or routing of messages (CloudEvents).

## Deployment

A more detailed tutorial is located [here](https://mico-docs.readthedocs.io/en/latest/setup/kubernetes/openfaas.html#function-deployment).


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

## Demo Function
The Messaging to HTTP Adapter FaaS function which is used in our demo is located in the Adapter/MessagingToHttpAdapter folder.
It requires the Cloud Event attributes `adapterRequestUrl`, `adapterRequestMethod` and expects messages of the type "httpEnvelop" with the content type "application/json". The [HTTP to Messaging Adapter Component](https://github.com/UST-MICO/http-to-messaging-adapter) produces the right messages. The function executes the HTTP request which is embedded in the message and produces a response message from the HTTP response. 
