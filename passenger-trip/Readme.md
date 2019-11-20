Automatically deploy everything:
#### Step 1
Push all images to Docker Hub
```
$  make docker-push-all 
``` 

#### Step 2
Deploy all yaml files to Google Cloud Kubernetes
```
$ make kubectl-apply-all
```

#### Cleaning up

```
$ make kubectl-delete-all
```
