Automatically deploy everything:
#### Step 1
Push all images to Docker Hub
```
$  make docker-push-all 
``` 

#### Step 2
Deploy all services to Google Cloud Kubernetes 
Also add Kafka Topics in kafka service for the service using
```
$ make kubectl-apply-all
```

#### Cleaning up

```
$ make kubectl-delete-all
```
