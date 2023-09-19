% ex2_1_1()
% ex2_1_2()ex2_1_3()

ex2_1_3()

function ex2_1_1()
    %ex2_1_1:����Ҷ�任
    % ������ű���
    syms t;
    % ����ĿҪ���庯��
    ft=sym(exp(-t).*heaviside(t));
    % תΪft�ĸ���Ҷ�任���ʽ
    Fw=fourier(ft);
    
    % ��������׶�Ӧ�ĺ���
    amplitude=abs(Fw);
    % ������ʾ����
    subplot(2,1,1);
    % ����ͼ��
    fplot(amplitude);
    % �������귶Χ
    axis([-8 8 0 1.2]);
    % ������
    grid on
    % ���ñ���
    title('Amplitude spectrum');
    % ���ú�������
    xlabel('w')

    % ������λ�׶�Ӧ�ĺ���
    phase=atan(imag(Fw)/real(Fw));
    % ������ʾ����
    subplot(2,1,2);
    % ����ͼ��
    fplot(phase);
    % �������귶Χ
    axis([-5 5 -1.5 1.5]);
    % ������
    grid on
    % ���ñ���
    title('Phase spectrum');
    % ���ú�������
    xlabel('w')
    
end

function ex2_1_2()
    % ex2_1_2:����Ҷ��任
    % ������ű���
    syms t;
    % ����F(w)��Ӧ�ķ��ű��ʽ
    Fw=str2sym('1/(1+w^2)');
    % תΪ����t�ı��ʽ
    ft=ifourier(Fw,t);
    % ����ͼ��
    fplot(ft);
    % ���ú������귶Χ���������ԣ�������[-5, 5]��������[0, 0.55]�Ϻ���
    axis([-5 5 0 0.55])
    % ������
    grid on
    % ���ñ���
    title('1+1/(1+w^2)��Ӧ�ĸ���Ҷ��任')
    % ���ú�������
    xlabel('w')

end

function test()
    %ex2_1_3:��ֵ���㣬����ֵ������۽�Ա�
    %��������
    N=10000;
    %s�趨ʱ��������
    t=-1:2/N:1;
    %����ʱ������
    dt=-40:2/N:40;
    %�趨Ƶ�׷�Χ
    w=-40:1:40;
    f=exp(-t).*heaviside(t);
    %���и���Ҷ�任
    F=1/N*f*exp(-1i*pi*t'*w);
    subplot(2,1,1);
    %���Ʒ�����
    stem(w,abs(F),'-+');
    axis([-8 8 0 1.2]);
    grid on;
    title('Amplitude spectrum');
    subplot(2,1,2);
    %������λ��
    stem(w,angle(F),'-+');
    axis([-5 5 -1.8 1.8]);
    grid on;
    title('Phase spectrum');
end

function ex2_1_3()
    N = 5000;%��������
    Ts=1/N;%�趨ʱ��������
    t=-15:1/N:15;%����ʱ����
    w=-15:0.01:15;%�趨Ƶ�׷�Χ
    ft=exp(-t).*heaviside(t);%ָ���ź�
    Fw=Ts*ft*exp(-1j*t'*w);%���и���Ҷ�任
     
    subplot(2,1,1);
    plot(w,abs(Fw))%����abs������÷�����
    % plot(w,amplitude,'r')
    axis([-8 8 0 1.3]) 
    grid on
    title('f(t)������');
    % ���ú�������
    xlabel('w')
    subplot(2,1,2);
    plot(w,angle(Fw));
    grid on
    title('f(t)��λ��');
    % ���ú�������
    xlabel('w')
    
end

    



