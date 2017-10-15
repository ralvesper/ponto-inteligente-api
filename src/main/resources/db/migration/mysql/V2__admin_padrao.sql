INSERT INTO `empresa` (`id`, `cnpj`, `data_atualizacao`, `data_criacao`, `razao_social`) 
VALUES (NULL, '82198127000121', CURRENT_DATE(), CURRENT_DATE(), 'Optimize IT');

INSERT INTO `funcionario` (`id`, `cpf`, `data_atualizacao`, `data_criacao`, `email`, `nome`, 
`perfil`, `qtd_horas_almoco`, `qtd_horas_trabalho_dia`, `senha`, `valor_hora`, `empresa_id`) 
VALUES (NULL, '16248890935', CURRENT_DATE(), CURRENT_DATE(), 'admin@optimize.com', 'ADMIN', 'ROLE_ADMIN', NULL, NULL, 
'$2a$10$0FNUY65TPry7Xz05zGY41O2sgi5Wo9iueBPj.X8bdLtEKHmoGCvZ6', NULL, 
(SELECT `id` FROM `empresa` WHERE `cnpj` = '82198127000121'));
