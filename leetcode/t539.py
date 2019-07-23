class Solution:
    def findMinDifference(self, timePoints):
        times = []
        for time in timePoints:
            hour_min = time.split(':')
            minutes = int(hour_min[0]) * 60 + int(hour_min[1])
            times.append(minutes)
        times = sorted(times, reverse=False)
        min_diff = times[0] + abs(times[-1] - (60 * 24))
        for index in range(0, len(times) - 1):
            min_diff = min(min_diff, times[index + 1] - times[index])
        return min_diff

print(Solution().findMinDifference(["00:00","23:59","00:00"]))
