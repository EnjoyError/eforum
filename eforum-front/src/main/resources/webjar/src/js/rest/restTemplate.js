function RestTemplate($http, $q) {
    this.$http = $http;
    this.$q = $q;
}

RestTemplate.fn = RestTemplate.prototype;

RestTemplate.fn.get = function(url, params) {
    var deferred = this.$q.defer();
    this.$http({
        method: 'GET',
        url: url,
        params: params
    }).success(function(data, status, headers, config) {
        deferred.resolve(data);
    }).error(function(data, status, headers, config) {
        deferred.reject(data);
    });
    return deferred.promise;
}

RestTemplate.fn.post = function(url, params) {
    params = params || {};
    var deferred = this.$q.defer();
    this.$http({
        method: 'POST',
        url: url,
        data: params
    }).success(function(data, status, headers, config) {
        deferred.resolve(data);
    }).error(function(data, status, headers, config) {
        deferred.reject(data);
    });
    return deferred.promise;
}

module.exports = RestTemplate;