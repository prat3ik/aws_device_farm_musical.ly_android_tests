import os, json
# os.system('search.username=username')
# os.system('mvn clean package -DskipTests=true')



user_email = 'abc@abc.com'
user_pass = 'testtesttest'
fan1 = '@11111'
fan2 = '@22222'
fan3 = '@33333'
comment_text='Text23456'
commnt_icon_x_location = '1024444'
commnt_icon_y_location = '204788'

print("1) Chagning the configuration values.....")
os.system("sed -i '' 's/valid.email=pratikchachpara@gmail.com/valid.email="+user_email+"/g' src/test/resources/configuration.properties")
os.system("sed -i '' 's/valid.password=pratikmusicly/valid.password="+user_pass+"/g' src/test/resources/configuration.properties")
os.system("sed -i '' 's/username.fan1=@ashbare22/username.fan1="+fan1+"/g' src/test/resources/configuration.properties")
os.system("sed -i '' 's/username.fan2=@piggylover4122007/username.fan2="+fan2+"/g' src/test/resources/configuration.properties")
os.system("sed -i '' 's/username.fan3=@lxdanielxl/username.fan3="+fan3+"/g' src/test/resources/configuration.properties")
os.system("sed -i '' 's/comment.text=Hello123/comment.text="+comment_text+"/g' src/test/resources/configuration.properties")
os.system("sed -i '' 's/Pixel.comment.icon_x=1007/Pixel.comment.icon_x="+commnt_icon_x_location+"/g' src/test/resources/configuration.properties")
os.system("sed -i '' 's/Pixel.comment.icon_y=1145/Pixel.comment.icon_y="+commnt_icon_y_location+"/g' src/test/resources/configuration.properties")

print("2) Packaging the Test cases Jar....")
os.system('mvn clean package -DskipTests=true')

print("3) Uploading test cases to AWS....")
os.system('curl -T target/zip-with-dependencies.zip "https://prod-us-west-2-uploads.s3-us-west-2.amazonaws.com/arn%3Aaws%3Adevicefarm%3Aus-west-2%3A871290565691%3Aproject%3A08ddbcf6-51e1-4c6c-9cee-6505d7b1d8aa/uploads/arn%3Aaws%3Adevicefarm%3Aus-west-2%3A871290565691%3Aupload%3A08ddbcf6-51e1-4c6c-9cee-6505d7b1d8aa/35122ac7-aca7-47b0-888e-ae44e3877555/musically.zip?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20180724T174519Z&X-Amz-SignedHeaders=host&X-Amz-Expires=86400&X-Amz-Credential=AKIAJSORV74ENYFBITRQ%2F20180724%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Signature=8a09a9a39c902a38884abfc2e50ecdfb9b290c8e18ada8b9cb96f0d955adab06"')

print("4) Getting the status of uploaded Zip file....")
status = False
arn_of_uploaded_zip = ''
while status == False:
	os.system("aws devicefarm get-upload --arn arn:aws:devicefarm:us-west-2:871290565691:upload:08ddbcf6-51e1-4c6c-9cee-6505d7b1d8aa/35122ac7-aca7-47b0-888e-ae44e3877555>zip_status.json")
	with open('zip_status.json') as data_file:    
		data = json.load(data_file)
	upload_status = data['upload']['status']	
	if upload_status=='SUCCEEDED':
		status = True
		arn_of_uploaded_zip = data['upload']['arn']
		print('build SUCCEEDED, ARN: '+arn_of_uploaded_zip)

# arn_of_uploaded_zip = "arn:aws:devicefarm:us-west-2:871290565691:upload:08ddbcf6-51e1-4c6c-9cee-6505d7b1d8aa/ac78e709-57dc-4b54-aa88-4883f25f2a52"
print("5) Scheduling run on AWS Device Farm.....")
schedule_run_command = 'aws devicefarm schedule-run --project-arn arn:aws:devicefarm:us-west-2:871290565691:project:08ddbcf6-51e1-4c6c-9cee-6505d7b1d8aa --app-arn arn:aws:devicefarm:us-west-2:871290565691:upload:08ddbcf6-51e1-4c6c-9cee-6505d7b1d8aa/fa1fcb61-4097-4a65-8bdf-c30c0dc71178 --device-pool-arn arn:aws:devicefarm:us-west-2:871290565691:devicepool:08ddbcf6-51e1-4c6c-9cee-6505d7b1d8aa/b5a5925b-a9d7-4759-9202-cf986d4f4358 --name firstMusicalLy --test ' +  '\'{"type": "APPIUM_JAVA_TESTNG","testPackageArn":"'+ arn_of_uploaded_zip+'"}\''
os.system(schedule_run_command)


print("6) Congratulations. Build started on AWS..!")

print("7) Resetting the changes made to configuraion file..")
os.system('git stash')

print("DONE!")


