build:
	@echo "Building application."
	docker-compose build interpreter

run:
	@echo "Running application."
	docker run -it pi:latest