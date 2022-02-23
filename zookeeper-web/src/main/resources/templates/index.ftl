<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.1/semantic.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.1/semantic.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/3.51/jquery.form.min.js"></script>
    <meta charset="UTF-8">
    <title>集群管理系统</title>
</head>
<body>
<div class="ui container ">
    <h1 class="ui center aligned header" style="margin-top: 20px">
        分布式集群管理系统
    </h1>
    <table class="ui  green  table">
        <thead>
        <tr>
            <th>pid</th>
            <th>ip</th>
            <th>系统名称</th>
            <th>系统版本</th>
            <th>架构</th>
            <th>CPU负载</th>
            <th>可用核数</th>
            <th>总物理内存</th>
            <th>已用物理内存</th>
            <th>剩余物理内存</th>
            <th>总交换空间</th>
            <th>已用交换空间</th>
            <th>剩余交换空间</th>
            <#--
            <th>占用内存</th>
            -->
            <#--
            <th>剩余内存</th>
            -->
        </tr>
        </thead>
        <tbody>
        <#list items as item>
            <tr>
                <td>${item.systemDetail.pid}</td>
                <td>${item.systemDetail.ip}</td>
                <td>${item.systemDetail.systemName}</td>
                <td>${item.systemDetail.systemVersion}</td>
                <td>${item.systemDetail.systemArch}</td>
                <td>${item.systemDetail.cpu}</td>
                <td>${item.systemDetail.availableProcessors}</td>
                <td>${item.systemDetail.totalPhysicalMemory}</td>
                <td>${item.systemDetail.usedPhysicalMemory}</td>
                <td>${item.systemDetail.freePhysicalMemory}</td>
                <td>${item.systemDetail.totalSwapSpace}</td>
                <td>${item.systemDetail.usedSwapSpace}</td>
                <td>${item.systemDetail.freeSwapSpace}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

</body>
</html>