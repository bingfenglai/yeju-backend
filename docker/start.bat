echo '����redis����'
start /d "G:\graduation project\yeju_code\yeju_dev\docker\" startRedisService.cmd
echo '��������'
start /d "G:\graduation project\yeju_code\yeju_dev\docker\" startGateway.cmd
echo '������֤�����ṩ��'
start /d "G:\graduation project\yeju_code\yeju_dev\docker\" startAuthService.cmd
echo '������֤����������"
start /d "G:\graduation project\yeju_code\yeju_dev\docker\" startAuthRestApi.cmd
