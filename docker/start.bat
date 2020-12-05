echo '启动redis服务'
start /d "G:\graduation project\yeju_code\yeju_dev\docker\" startRedisService.cmd
echo '启动网关'
start /d "G:\graduation project\yeju_code\yeju_dev\docker\" startGateway.cmd
echo '启动认证服务提供者'
start /d "G:\graduation project\yeju_code\yeju_dev\docker\" startAuthService.cmd
echo '启动认证服务消费者"
start /d "G:\graduation project\yeju_code\yeju_dev\docker\" startAuthRestApi.cmd
