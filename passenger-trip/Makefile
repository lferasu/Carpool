docker-push-all:
	cd ./frontend && $(MAKE) docker-push
	cd ./backend && $(MAKE) docker-push

kubectl-apply-all:
	kubectl apply -f ./frontend/manifests/
	kubectl apply -f ./backend/manifests/

kubectl-delete-all:
	kubectl delete -f ./backend/manifests/
	kubectl delete -f ./frontend/manifests/

kubectl-apply-kafka-zookeeper:
	cd ./deployment && $(MAKE) K8S-install && $(MAKE) K8S-add-topic


